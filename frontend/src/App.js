import React, { useState, useEffect } from 'react';
import shelfLife from './shelfLife';
import './styles.css';

const API_BASE_URL = 'http://localhost:8080/api/items';

const getStatus = (expiry) => {
  const today = new Date();
  const diff = (new Date(expiry) - today) / (1000 * 60 * 60 * 24);
  if (diff < 0) return 'expired';
  if (diff <= 3) return 'near';
  return 'safe';
};

const App = () => {
  const [items, setItems] = useState([]);
  const [name, setName] = useState('');
  const [expiry, setExpiry] = useState('');
  const [editingId, setEditingId] = useState(null);
  const [filter, setFilter] = useState('all');
  const [wasted, setWasted] = useState([]);
  const [darkMode, setDarkMode] = useState(false);

  // Fetch items and wasted items on component mount
  useEffect(() => {
    fetchItems();
    fetchWastedItems();
  }, []);

  const fetchItems = async () => {
    try {
      const response = await fetch(API_BASE_URL);
      const data = await response.json();
      setItems(data);
    } catch (error) {
      console.error('Error fetching items:', error);
    }
  };

  const fetchWastedItems = async () => {
    try {
      const response = await fetch(`${API_BASE_URL}/wasted`);
      const data = await response.json();
      setWasted(data);
    } catch (error) {
      console.error('Error fetching wasted items:', error);
    }
  };

  useEffect(() => {
    if (name.toLowerCase() in shelfLife && !expiry) {
      const days = shelfLife[name.toLowerCase()];
      const suggestDate = new Date();
      suggestDate.setDate(suggestDate.getDate() + days);
      setExpiry(suggestDate.toISOString().split('T')[0]);
    }
  }, [name]);

  const handleSubmit = async () => {
    if (!name || !expiry) return;
    const itemData = { name, expiry };
    
    try {
      if (editingId !== null) {
        // Update existing item
        const response = await fetch(`${API_BASE_URL}/${editingId}`, {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(itemData)
        });
        if (response.ok) {
          fetchItems();
          setEditingId(null);
        }
      } else {
        // Create new item
        const response = await fetch(API_BASE_URL, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(itemData)
        });
        if (response.ok) {
          fetchItems();
        }
      }
      setName('');
      setExpiry('');
    } catch (error) {
      console.error('Error saving item:', error);
    }
  };

  const deleteItem = async (id) => {
    try {
      const response = await fetch(`${API_BASE_URL}/${id}`, {
        method: 'DELETE'
      });
      if (response.ok) {
        fetchItems();
      }
    } catch (error) {
      console.error('Error deleting item:', error);
    }
  };

  const editItem = (item) => {
    setName(item.name);
    setExpiry(item.expiry);
    setEditingId(item.id);
  };

  const markWasted = async (id) => {
    try {
      const response = await fetch(`${API_BASE_URL}/${id}/waste`, {
        method: 'POST'
      });
      if (response.ok) {
        fetchItems();
        fetchWastedItems();
      }
    } catch (error) {
      console.error('Error marking item as wasted:', error);
    }
  };

  const filteredItems = items
    .filter(item => {
      const status = getStatus(item.expiry);
      return filter === 'all' || status === filter;
    })
    .sort((a, b) => new Date(a.expiry) - new Date(b.expiry));

  return (
    <div className={`container ${darkMode ? 'dark' : ''}`}>
      <div className="header">
        <h1>ExpiryAlert</h1>
        <button className="dark-toggle" onClick={() => setDarkMode(!darkMode)}>
          {darkMode ? '☀️ Light Mode' : '🌙 Dark Mode'}
        </button>
      </div>

      <div className="input-group">
        <input placeholder="Item name" value={name} onChange={(e) => setName(e.target.value)} />
        <input type="date" value={expiry} onChange={(e) => setExpiry(e.target.value)} />
        <button onClick={handleSubmit}>{editingId !== null ? 'Update' : 'Add'}</button>
      </div>

      <div className="filters">
        {['all', 'safe', 'near', 'expired'].map(type => (
          <button
            key={type}
            onClick={() => setFilter(type)}
            className={filter === type ? 'active-filter' : ''}
          >
            {type.toUpperCase()}
          </button>
        ))}
      </div>

      <ul className="item-list">
        {filteredItems.map((item) => {
          const status = getStatus(item.expiry);
          return (
            <li key={item.id} className={`item ${status}`}>
              <div className="item-info">
                <strong>{item.name}</strong> – {item.expiry}
              </div>
              <div className="item-actions">
                <button onClick={() => editItem(item)}>✏️</button>
                <button onClick={() => deleteItem(item.id)}>❌</button>
                <button onClick={() => markWasted(item.id)}>🗑️</button>
              </div>
            </li>
          );
        })}
      </ul>

      <h3>Wasted Items: {wasted.length}</h3>
      <ul className="wasted-list">
        {wasted.map((item) => (
          <li key={item.id}>{item.name} – {item.expiry}</li>
        ))}
      </ul>
    </div>
  );
};

export default App;

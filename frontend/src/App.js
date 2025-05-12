import React, { useState, useEffect } from 'react';
import './styles.css';

function App() {
  const [items, setItems] = useState([]);
  const [newItem, setNewItem] = useState({ name: '', expiry: '' });
  const [message, setMessage] = useState('');

  useEffect(() => {
    fetchItems();
  }, []);

  const fetchItems = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/items');
      const data = await response.json();
      setItems(data);
    } catch (error) {
      setMessage('Error fetching items: ' + error.message);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/api/items', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newItem),
      });
      
      if (response.ok) {
        setMessage('Item added successfully!');
        setNewItem({ name: '', expiry: '' });
        fetchItems();
      } else {
        setMessage('Error adding item');
      }
    } catch (error) {
      setMessage('Error: ' + error.message);
    }
  };

  const getExpiryStatus = (expiryDate) => {
    const today = new Date();
    const expiry = new Date(expiryDate);
    const diffTime = expiry - today;
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

    if (diffDays < 0) return 'expired';
    if (diffDays <= 3) return 'warning';
    if (diffDays <= 7) return 'soon';
    return 'ok';
  };

  const getExpiryClass = (status) => {
    switch (status) {
      case 'expired': return 'expiry-warning';
      case 'warning': return 'expiry-warning';
      case 'soon': return 'expiry-soon';
      default: return 'expiry-ok';
    }
  };

  return (
    <div className="container">
      <h1>Expiry Alert System</h1>

      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="name">Item Name:</label>
          <input
            type="text"
            id="name"
            value={newItem.name}
            onChange={(e) => setNewItem({ ...newItem, name: e.target.value })}
            placeholder="Enter item name"
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="expiry">Expiry Date:</label>
          <input
            type="date"
            id="expiry"
            value={newItem.expiry}
            onChange={(e) => setNewItem({ ...newItem, expiry: e.target.value })}
            required
          />
        </div>
        <button type="submit">Add Item</button>
      </form>

      {message && (
        <div className={`alert ${message.includes('Error') ? 'alert-error' : 'alert-success'}`}>
          {message}
        </div>
      )}

      <div className="item-list">
        <h2>Your Items</h2>
        {items.map((item) => {
          const status = getExpiryStatus(item.expiry);
          const statusClass = getExpiryClass(status);
          return (
            <div key={item.id} className="item-card">
              <div className="item-name">{item.name}</div>
              <div className={`item-expiry ${statusClass}`}>
                Expires: {new Date(item.expiry).toLocaleDateString()}
                {status === 'expired' && ' (Expired)'}
                {status === 'warning' && ' (Expiring soon!)'}
                {status === 'soon' && ' (Expiring in a week)'}
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default App;

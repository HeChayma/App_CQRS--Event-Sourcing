import React, { useState } from 'react';
import { createAccount } from '../services/api';

const CreateAccount = () => {
    const [balance, setBalance] = useState(0);
    const [currency, setCurrency] = useState('USD');
    const [message, setMessage] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await createAccount(balance, currency);
            setMessage(`Account Created with ID: ${response.data}`);
        } catch (error) {
            setMessage('Error creating account');
            console.error(error);
        }
    };

    return (
        <div className="card">
            <h2>Create New Account</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Initial Balance</label>
                    <input
                        type="number"
                        value={balance}
                        onChange={(e) => setBalance(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label>Currency</label>
                    <select value={currency} onChange={(e) => setCurrency(e.target.value)}>
                        <option value="USD">USD</option>
                        <option value="EUR">EUR</option>
                        <option value="MAD">MAD</option>
                    </select>
                </div>
                <button type="submit" className="btn">Create Account</button>
            </form>
            {message && <p className="message">{message}</p>}
        </div>
    );
};

export default CreateAccount;

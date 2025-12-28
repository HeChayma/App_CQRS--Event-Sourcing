import React, { useState } from 'react';
import { creditAccount, debitAccount } from '../services/api';

const TransactionForm = () => {
    const [accountId, setAccountId] = useState('');
    const [amount, setAmount] = useState(0);
    const [currency, setCurrency] = useState('USD');
    const [type, setType] = useState('CREDIT');
    const [message, setMessage] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            if (type === 'CREDIT') {
                await creditAccount(accountId, amount, currency);
            } else {
                await debitAccount(accountId, amount, currency);
            }
            setMessage(`Transaction (${type}) successful`);
        } catch (error) {
            setMessage('Error performing transaction');
            console.error(error);
        }
    };

    return (
        <div className="card">
            <h2>Perform Transaction</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Account ID</label>
                    <input
                        type="text"
                        value={accountId}
                        onChange={(e) => setAccountId(e.target.value)}
                        placeholder="Enter Account ID"
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Amount</label>
                    <input
                        type="number"
                        value={amount}
                        onChange={(e) => setAmount(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Transaction Type</label>
                    <select value={type} onChange={(e) => setType(e.target.value)}>
                        <option value="CREDIT">Credit</option>
                        <option value="DEBIT">Debit</option>
                    </select>
                </div>
                <button type="submit" className="btn">Submit Transaction</button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );
};

export default TransactionForm;

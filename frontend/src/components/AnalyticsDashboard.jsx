import React, { useEffect, useState } from 'react';
import { getAllAnalytics } from '../services/api';

const AnalyticsDashboard = () => {
    const [data, setData] = useState([]);

    useEffect(() => {
        loadData();
    }, []);

    const loadData = async () => {
        try {
            const response = await getAllAnalytics();
            setData(response.data);
        } catch (error) {
            console.error("Error loading analytics", error);
        }
    };

    return (
        <div className="card">
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                <h2>Analytics Dashboard</h2>
                <button onClick={loadData} className="btn btn-secondary">Refresh</button>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>Account ID</th>
                        <th>Total Credited</th>
                        <th>Total Debited</th>
                        <th>Current Balance</th>
                        <th>Tx Count</th>
                    </tr>
                </thead>
                <tbody>
                    {data.map((item) => (
                        <tr key={item.accountId}>
                            <td>{item.accountId}</td>
                            <td>{item.totalCredited}</td>
                            <td>{item.totalDebited}</td>
                            <td>{item.currentBalance}</td>
                            <td>{item.transactionCount}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default AnalyticsDashboard;

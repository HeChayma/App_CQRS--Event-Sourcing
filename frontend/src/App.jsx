import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import CreateAccount from './components/CreateAccount';
import TransactionForm from './components/TransactionForm';
import AnalyticsDashboard from './components/AnalyticsDashboard';
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        <nav>
          <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="/analytics">Analytics</Link></li>
          </ul>
        </nav>

        <Routes>
          <Route path="/" element={
            <div className="dashboard-grid">
              <div className="card">
                <h2>Create Account</h2>
                <CreateAccount />
              </div>
              <div className="card">
                <h2>Transactions</h2>
                <TransactionForm />
              </div>
            </div>
          } />
          <Route path="/analytics" element={<AnalyticsDashboard />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;

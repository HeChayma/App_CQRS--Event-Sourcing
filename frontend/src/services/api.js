import axios from 'axios';

const ACCOUNT_API_BASE_URL = "http://localhost:8081/commands/account";
const QUERY_API_BASE_URL = "http://localhost:8081/query/account";
const ANALYTICS_API_BASE_URL = "http://localhost:8082/analytics";

export const createAccount = (initialBalance, currency) => {
    return axios.post(`${ACCOUNT_API_BASE_URL}/create`, { initialBalance, currency });
};

export const creditAccount = (accountId, amount, currency) => {
    return axios.put(`${ACCOUNT_API_BASE_URL}/credit/${accountId}`, { amount, currency });
};

export const debitAccount = (accountId, amount, currency) => {
    return axios.put(`${ACCOUNT_API_BASE_URL}/debit/${accountId}`, { amount, currency });
};

export const getAccount = (accountId) => {
    return axios.get(`${QUERY_API_BASE_URL}/${accountId}`);
};

export const getAllAnalytics = () => {
    return axios.get(`${ANALYTICS_API_BASE_URL}/all`);
};

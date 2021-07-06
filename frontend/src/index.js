import React from 'react';
import ReactDOM from 'react-dom';
import './css/index.css';
import App from './App';
import AppProvider from './context/AppProvider';
import reportWebVitals from './reportWebVitals';

ReactDOM.render(
    <AppProvider>
        <React.StrictMode>
            <App/>
        </React.StrictMode>
    </AppProvider>,
    document.getElementById('root')
);
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

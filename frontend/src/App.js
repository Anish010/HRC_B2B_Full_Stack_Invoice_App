import './App.css';
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import DataGridComp from './components/DataGridComp';
import AddData from './components/AddData';
import Header from './components/Header';
import Footer from './components/Footer';
import Navigation from './components/Navigation';
import SearchData from './components/searchData';
import OrderIdContextProvider from './Context/OrderIdContext';


const App = () => {
  return (
    <div className="App">
      <Router>
        <OrderIdContextProvider> {/* Wrap the components with OrderIdContextProvider */}
          <div className="content">
            <Header />
            <Navigation />
            <Routes>
              <Route path="/" element={<DataGridComp />} />
              <Route path="/add" element={<AddData />} />
              <Route path="/searchData" element={<SearchData />} />
              {/* <Route path="/update/:customerOrderId" element={<EditInvoice />} /> Pass the customerOrderId as a URL parameter */}
            </Routes>
          </div>
          <Footer />
        </OrderIdContextProvider>
      </Router>
    </div>
  );
};

export default App;

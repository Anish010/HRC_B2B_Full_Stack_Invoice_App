import React, { createContext, useState } from 'react';

export const OrderIdContext = createContext();

const OrderIdContextProvider = ({ children }) => {
  const [customerOrderId, setCustomerOrderId] = useState('');

  const handleSetOrderId = (orderId) => {
    setCustomerOrderId(orderId);
  };

  return (
    <OrderIdContext.Provider value={{ customerOrderId, setCustomerOrderId: handleSetOrderId }}>
      {children}
    </OrderIdContext.Provider>
  );
};

export default OrderIdContextProvider;

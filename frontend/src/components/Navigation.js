import React, { useContext, useState } from 'react';
import { AppBar, Button, Toolbar } from '@material-ui/core';
import { Link, useNavigate } from 'react-router-dom';
import { OrderIdContext } from '../Context/OrderIdContext';

const Navigation = () => {
  const [searchResultTabVisible, setSearchResultTabVisible] = useState(false);
  const { customerOrderId, setCustomerOrderId } = useContext(OrderIdContext);
  const navigate = useNavigate();

  const handleSearch = () => {
    navigate(`/searchData`);
    setSearchResultTabVisible(true);
  };

  const handleClear = () => {
    navigate('/');
    setSearchResultTabVisible(false);
  };

  const handleKeyDown = (event) => {
    if (event.key === 'Enter') {
      handleSearch();
    }
  };

  return (
    <div className="navigationPanel">
      <AppBar position="static">
        <Toolbar>
          <Button id="HomeBtn" className="Btn" component={Link} to="/" color="inherit" onClick={handleClear}>
            Home
          </Button>
          <Button id="AddBtn" className="Btn" component={Link} to="/add" color="inherit">
            Add
          </Button>
          {searchResultTabVisible ? (
            <>
            <Button id="SearchResultBtn" className="Btn"  component={Link} to="/searchResult" color="inherit">
              Search Result
            </Button>
            <Button  id="AnalyticsBtn" className="Btn" component={Link} to="/analytics" color="inherit">
              Analytics
              </Button>
              </>
          ) : (
            <Button  id="AnalyticsBtn" className="Btn" component={Link} to="/analytics" color="inherit">
              Analytics
            </Button>
          )}

            <input
              type="text"
              placeholder="Search Customer Order ID"
              value={customerOrderId}
              onChange={(e) => setCustomerOrderId(e.target.value)}
              onKeyDown={handleKeyDown}
              id="searchInput"
            />
            {searchResultTabVisible ? (
              <Button id="ClearBtn" variant="contained" onClick={handleClear}>
                Clear
              </Button>
            ) : (
              <Button id="advanceSearchBtn" variant="contained" >
                Advanced Search
              </Button>
            )}

        </Toolbar>
      </AppBar>
    </div>
  );
};

export default Navigation;

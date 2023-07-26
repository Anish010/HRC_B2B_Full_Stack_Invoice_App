import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { makeStyles } from '@material-ui/core/styles';
import columns from "./ColumnName";
import { Button, Grid, Modal, TextField } from '@material-ui/core';
import { DataGrid, GridPagination, GridToolbar, GridFooterContainer } from '@mui/x-data-grid';
import GridFooter from './GridFooter';

const useStyles = makeStyles({
  dataTable: {
    '& .MuiDataGrid-checkboxInput': {
      color: 'white',
    },
    '& .MuiDataGrid-columnSeparator': {
      display: 'none',
    },
  },
  customFooter: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-start',
    padding: '8px',
  },
  button: {
    marginRight: '8px',
  },
  modal: {
    borderRadius: '6px',
    position: 'absolute',
    top: '40%',
    right: '50%',
    width: '500px',
    height: '210px',
    backgroundColor: 'white',
    padding: '20px',
  },
});

const DataGridCompTemp = () => {
  const classes = useStyles();
  const [rows, setRows] = useState([]);
  const [pageSize, setPageSize] = useState(8);
  const [loading, setLoading] = useState(true);
  const [selectionRows, setSelectionRows] = useState([]);
  const [selectionRowsData, setSelectionRowsData] = useState(null);

  useEffect(() => {
    const fetchRows = (response) => {
      return response.data.map((item) => ({
        id: item.members.id,
        CUSTOMER_ORDER_ID: item.members.customerOrderId,
        SALES_ORG: item.members.salesOrg,
        DISTRIBUTION_CHANNEL: item.members.distributionChannel,
        COMPANY_CODE: item.members.companyCode,
        ORDER_CREATION_DATE: item.members.orderCreationDate,
        ORDER_AMOUNT: item.members.orderAmount,
        ORDER_CURRENCY: item.members.orderCurrency,
        CUSTOMER_NUMBER: item.members.customerNumber,
        AMOUNT_IN_USD: item.members.amountInUsd,
      }));
    };

    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8086/b2b_backend/fetchData');
        setRows(fetchRows(response));
        setLoading(false);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  const handleSelectionModelChange = (newSelectionModel) => {
    setSelectionRows(newSelectionModel); // Update selected rows

    if (newSelectionModel.length === 1) {
      const selectionRow = rows.find((row) => row.id === newSelectionModel[0]);
      setSelectionRowsData(selectionRow);
    } else {
      setSelectionRowsData(null);
    }
  };

  const handleButtonClick = (label) => {
    if (label === "Refresh") {
      window.location.reload();
    } else if (label === 'Edit') {
      // Implement the logic for the 'Edit' button click
      console.log('Edit button clicked');
    }
  };

  return (
    <div className="outerBody">
      <div style={{ height: 560, width: '100%' }}>
        <DataGrid
          rows={rows}
          columns={columns}
          pageSize={pageSize}
          onPageSizeChange={(newPageSize) => setPageSize(newPageSize)}
          rowsPerPageOptions={[8, 10, 20, 50, 100]}
          checkboxSelection
          disableSelectionOnClick
          className={classes.dataTable}
          components={{
            Footer: (props) => (
              <GridFooter
                paginationProps={props}
                handleButtonClick={handleButtonClick}
              />
            ),
          }}
          onSelectionModelChange={handleSelectionModelChange}
          selectionModel={selectionRows}
        />
      </div>
    </div>
  );
};

export default DataGridCompTemp;
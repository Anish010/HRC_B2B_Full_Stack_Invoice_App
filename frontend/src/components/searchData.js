import React, { useState, useEffect, useContext } from 'react';
import axios from 'axios';
import { makeStyles } from '@material-ui/core/styles';
import columns from "./ColumnName";
import { OrderIdContext } from '../Context/OrderIdContext';
import { Button, Grid, Modal, TextField } from '@material-ui/core';
import { DataGrid, GridPagination, GridFooterContainer } from '@mui/x-data-grid';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import { useNavigate } from "react-router-dom";
import CircularProgress from '@material-ui/core/CircularProgress';

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

const CustomFooter = ({ paginationProps, selectionRowsData, setSelectionRows, customerOrderId, setRows, fetchRows }) => {
  const classes = useStyles();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [distributionChannel, setDistributionChannel] = useState('');
  const [companyCode, setCompanyCode] = useState('');
  const [orderCurrency, setOrderCurrency] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [countdown, setCountdown] = useState(6);

  const [successSnackOpen, setSuccessSnackOpen] = useState(false);
  const [successEditSnackOpen, setSuccessEditSnackOpen] = useState(false);
  const [errorSnackOpen, setErrorSnackOpen] = useState(false);
  const navigate = useNavigate();
  const [isEdited, setIsEdited] = useState(false);


  function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
  }

  const fetchData = async () => {
    setIsLoading(true);
    try {
      const response = await axios.post(`http://localhost:8086/b2b_backend/searchData?customerOrderId=${customerOrderId}`)
      setRows(fetchRows(response));
      return 1;
    } catch (error) {
      console.error("Error while fetching data from the API:", error);
        setIsLoading(false);
    }
  };

  const deleteData = () => {
    const customerOrderId = selectionRowsData.CUSTOMER_ORDER_ID;
    const data = { customerOrderId };
    setIsLoading(true);
    try {
      const response = axios.delete('b2b_backend/deleteInvoice', { data });
      console.log(response);
      setSuccessSnackOpen(true);
      setTimeout(() => {
        navigate('/');
      }, 5000);
    } catch (error) {
      console.error('Error deleting data:', error);
    } finally {
      setIsLoading(false);
    }
  }

  const handleButtonClick = (label) => {
    if (label === "Refresh") {
      fetchData();
    } else if (label === 'Edit') {
      if (!selectionRowsData) {
        setErrorSnackOpen(true);
        return;
      }
      setIsModalOpen(true);
      setOrderCurrency(selectionRowsData?.ORDER_CURRENCY || '');
      setCompanyCode(selectionRowsData?.COMPANY_CODE || '');
      setDistributionChannel(selectionRowsData?.DISTRIBUTION_CHANNEL || '');
    } else if (label === 'Delete') {
      if (!selectionRowsData) {
        setErrorSnackOpen(true);
        return;
      }
      deleteData();

    }
  };

  const handleSnackClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }

    setSuccessSnackOpen(false);
    setErrorSnackOpen(false);
    
  };
const handleEditModal = async (e) => {
    e.preventDefault();
    const customerOrderId = selectionRowsData.CUSTOMER_ORDER_ID;
    const data = {
      customerOrderId,
      distributionChannel,
      companyCode,
      orderCurrency,
    };
    setIsLoading(true);
    setIsModalOpen(false);
  try {
      setIsEdited(true);
      const response = await axios.put('b2b_backend/update', data);
      let check = fetchData();
      if (check === 1)
      {
        setIsEdited(true);
        }
      setSelectionRows([]);
    } catch (error) {
      console.error('Error updating data:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };
  
  useEffect(() => {
    const timer = setInterval(() => {
      setCountdown(prevCountdown => prevCountdown - 1);
    }, 1000);

    return () => {
      clearInterval(timer);
    };
  }, []);

  return (
    <>
      <GridFooterContainer className={classes.customFooter}>
        <div>
          <Button className="FooterButton" id="RefreshBtn" variant="outlined" onClick={() => handleButtonClick('Refresh')}>
            Refresh
          </Button>
          <Button className="FooterButton" id="EditBtn" variant="outlined" onClick={() => handleButtonClick('Edit')}>
            Edit
          </Button>
          <Button className="FooterButton" id="DeleteBtn" variant="outlined" onClick={() => handleButtonClick('Delete')}>
            Delete
          </Button>
          <Button className="FooterButton" id="PredictBtn" variant="outlined" onClick={() => handleButtonClick('Predict')}>
            Predict
          </Button>

          {isLoading ?
            <span>
              <CircularProgress size={30} id="loadingIcon" /></span> : null}

          <Snackbar open={successSnackOpen} autoHideDuration={7000} onClose={handleSnackClose}>
            <Alert onClose={handleSnackClose} severity="success">
              Invoice Deleted successfully!
              Redirecting to Home Page in {countdown} sec
            </Alert>
          </Snackbar>

          <Snackbar open={errorSnackOpen} autoHideDuration={3000} onClose={handleSnackClose}>
            <Alert onClose={handleSnackClose} severity="error">
              Error! No rows are selected.
            </Alert>
          </Snackbar>

            {isEdited ?
          <Snackbar open={true} autoHideDuration={7000} onClose={handleSnackClose}>
            <Alert onClose={handleSnackClose} severity="success">
              Invoice Edited successfully !
            </Alert>
            </Snackbar> : null}
          
        </div>
        <GridPagination {...paginationProps} />
      </GridFooterContainer>
      <Modal open={isModalOpen} onClose={handleCloseModal}>
        <div className={classes.modal}>
          <span style={{ fontSize: "22px" }}>Edit</span>
          <form onSubmit={handleEditModal}>
            <Grid container spacing={2} className="gridRow">
              {/* First Row */}
              <Grid item xs={6}>
                <TextField
                  label="ORDER CURRENCY"
                  variant="outlined"
                  fullWidth
                  value={orderCurrency}
                  onChange={(e) => {
                    setOrderCurrency(e.target.value);
                  }}
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  label="COMPANY CODE"
                  variant="outlined"
                  fullWidth
                  name="companyCode"
                  value={companyCode}
                  onChange={(e) => {
                    setCompanyCode(e.target.value);
                  }}
                />
              </Grid>

              {/* Second Row */}
              <Grid item xs={12}>
                <TextField
                  label="DISTRIBUTION CHANNEL"
                  variant="outlined"
                  fullWidth
                  name="distributionChannel"
                  value={distributionChannel}
                  onChange={(e) => {
                    setDistributionChannel(e.target.value);
                  }}
                />
              </Grid>

              <Grid item xs={6}>
                <Button type="submit" variant="outlined" className="ModalEditBtn" fullWidth>
                  EDIT
                </Button>
              </Grid>
              <Grid item xs={6}>
                <Button variant="outlined" className="ModalCancelBtn" fullWidth onClick={() => handleCloseModal()} >
                  CANCEL
                </Button>
              </Grid>
            </Grid>
          </form>
        </div>
      </Modal>
    </>
  );
};

const SearchData = () => {
  const classes = useStyles();
  const [rows, setRows] = useState([]);
  const [pageSize, setPageSize] = useState(8);
  const { customerOrderId } = useContext(OrderIdContext);
  const [selectionRows, setSelectionRows] = useState([]);
  const [selectionRowsData, setSelectionRowsData] = useState(null);

  const fetchRows = (response) => {
    return response.data.map(item => ({
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

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = () => {
    axios.post(`http://localhost:8086/b2b_backend/searchData?customerOrderId=${customerOrderId}`)
      .then((response) => {
        setRows(fetchRows(response));
      })
      .catch((error) => {
        console.error("Error while fetching data from the API:", error);
      });
  };

  const handleSelectionModelChange = (newSelectionModel) => {
    setSelectionRows(newSelectionModel); // Update selected rows

    if (newSelectionModel.length === 1) {
      const selectionRow = rows.find((row) => row.id === newSelectionModel[0]);
      setSelectionRowsData(selectionRow);
    } else {
      setSelectionRowsData(null);
    }
  };

  return (
    <div className="outerBody">
      <div style={{ height: '200px', width: '100%' }}>
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
            Footer: (props) => <CustomFooter paginationProps={props} setSelectionRows={setSelectionRows} selectionRowsData={selectionRowsData} customerOrderId={customerOrderId} setRows={setRows} fetchRows={fetchRows} />,
          }}
          onSelectionModelChange={handleSelectionModelChange}
          selectionModel={selectionRows}
        />
      </div>
    </div>
  );
};

export default SearchData;

import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { Button, Grid, Modal, TextField } from '@material-ui/core';
import axios from 'axios';
import CircularProgress from '@material-ui/core/CircularProgress';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';



const useStyles = makeStyles({
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
  loadingIcon: {
    display: 'flex',
    color: 'white',
  },
});

function Alert(props) {
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}

const EditModal = ({ isModalOpen,
  handleCloseModal,
  selectionRowsData,
  setSelectionRowsData,
  orderCurrency,
  setOrderCurrency,
  companyCode,
  setCompanyCode,
  distributionChannel,
  setDistributionChannel, }) => {
  const classes = useStyles();
  // const [distributionChannel, setDistributionChannel] = useState('');
  // const [companyCode, setCompanyCode] = useState('');
  // const [orderCurrency, setOrderCurrency] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [countdown, setCountdown] = useState(6);
  const [successSnackOpen, setSuccessSnackOpen] = useState(false);
  const [errorSnackOpen, setErrorSnackOpen] = useState(false);

  const handleEditModal = async (e) => {
    e.preventDefault();

    const customerOrderId = selectionRowsData.CUSTOMER_ORDER_ID;
    console.log(customerOrderId, distributionChannel, companyCode, orderCurrency);
    const data = {
      customerOrderId,
      distributionChannel,
      companyCode,
      orderCurrency,
    };
    setIsLoading(true);
    handleCloseModal();
    try {
      const response = await axios.put('b2b_backend/update', data);
      console.log(response);
    } catch (error) {
      console.error('Error updating data:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const handleClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }

    setSuccessSnackOpen(false);
    setErrorSnackOpen(false);
  };

  return (
    <>
      
    <Modal open={isModalOpen} onClose={handleCloseModal}>
      <div className={classes.modal}>
        <span style={{ fontSize: '22px' }}>Edit</span>
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
              <Button variant="outlined" className="ModalEditBtn" onClick={handleEditModal} fullWidth>
                EDIT
              </Button>
            </Grid>
            <Grid item xs={6}>
              <Button variant="outlined" className="ModalCancelBtn" fullWidth onClick={handleCloseModal}>
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

export default EditModal;

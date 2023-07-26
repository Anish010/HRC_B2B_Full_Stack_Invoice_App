import React, { useState } from "react";
import axios from "axios";
import { Button, Grid, TextField } from '@material-ui/core';
import { useNavigate } from "react-router-dom";

const AddData = () => {
const navigate = useNavigate(); 
    const [inputData, setInputData] = useState({
        distributionChannel: "",
        orderCreationDate: "",
        orderCurrency: "",
        customerNumber: "",
    })
    const handleData = (e) => {
        setInputData({ ...inputData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        axios
            .post("http://localhost:8086/b2b_backend/addData", inputData)
            .then((response) => {
                console.log("Data successfully posted to the database:", response.data);

            })
            .catch((error) => {
                console.error("Error while posting data to the database:", error);

            });
        console.log(inputData);
       navigate('/')
    };

    return (
        <div className="addInvoiceBody">
            <form onSubmit={handleSubmit}>
                <Grid container spacing={2} className="gridRow">
                    {/* First Row */}
                    <Grid item xs={2}>
                        <TextField type="number" className="addTextField" label="CUSTOMER ORDER ID" name="customerOrderId" variant="filled" value={inputData.customerOrderId} onChange={handleData} fullWidth />
                    </Grid>
                    <Grid item xs={2}>
                        <TextField type="number" className="addTextField" label="SALES ORG"  name="salesOrg" value={inputData.salesOrg} onChange={handleData} variant="filled" fullWidth />
                    </Grid>
                    <Grid item xs={8}>
                        <TextField className="addTextField" label="DISTRIBUTION CHANNEL" name="distributionChannel" value={inputData.distributionChannel} onChange={handleData} variant="filled" fullWidth />
                    </Grid>

                    {/* Second Row */}
                    <Grid item xs={2}>
                        <TextField type="number" className="addTextField" label="CUSTOMER NUMBER" name="customerNumber" value={inputData.customerNumber} onChange={handleData} variant="filled" fullWidth />
                    </Grid>
                    <Grid item xs={2}>
                        <TextField type="number"  className="addTextField" label="COMPANY CODE"  name="companyCode" value={inputData.companyCode} onChange={handleData} variant="filled" fullWidth />
                    </Grid>
                    <Grid item xs={2}>
                        <TextField className="addTextField" label="ORDER CURRENCY" name="orderCurrency" value={inputData.orderCurrency} onChange={handleData} variant="filled" fullWidth />
                    </Grid>
                    <Grid item xs={2}>
                        <TextField className="addTextField" label="AMOUNT IN USD" name="amountInUsd" value={inputData.amountInUsd} onChange={handleData} variant="filled" fullWidth />
                    </Grid>
                    <Grid item xs={4}>
                        <TextField className="addTextField" label="ORDER CREATION DATE" name="orderCreationDate" value={inputData.orderCreationDate} onChange={handleData} variant="filled" fullWidth type="date" />
                    </Grid>

                    <Grid item xs={6}>
                        <Button variant="contained" className="addBtn" onClick={handleSubmit} color="primary" fullWidth>
                            ADD
                        </Button>
                    </Grid>
                    <Grid item xs={6}>
                        <Button variant="contained" className="clearDataBtn" fullWidth>
                            CLEAR DATA
                        </Button>
                    </Grid>
                </Grid>
            </form>
        </div>
    );
};

export default AddData;

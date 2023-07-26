const columns = [
  {
    field: 'id',
    headerName: 'SL NO',
    headerAlign: 'left',
    align: 'left',
    type: 'number', width: 115,
  },
  {
    field: 'CUSTOMER_ORDER_ID',

    headerName: 'CUSTOMER ORDER ID',
    headerAlign: 'left',
    align: 'left',
    type: 'number',
    width: 210,
    editable: true,
  },
  {
    field: 'SALES_ORG',

    headerName: 'SALES ORG',
    type: 'number',
    headerAlign: 'left',
    align: 'left',
    width: 150,
    editable: true,
  },
  {
    field: 'DISTRIBUTION_CHANNEL',

    headerName: 'DISTRIBUTION CHANNEL',
    headerAlign: 'left',
    align: 'left',
    width: 230,
    editable: true,
  },
  {
    field: 'COMPANY_CODE',

    headerName: 'COMPANY CODE',
    type: 'number',
    headerAlign: 'left',
    align: 'left',
    sortable: true,
    width: 180,
    editable: true,
  },
  {
    field: 'ORDER_CREATION_DATE',

    headerName: 'ORDER CREATION DATE',
    sortable: true,
    headerAlign: 'left',
    align: 'left',
    width: 225,
    editable: true,
  },
  {
    field: 'ORDER_AMOUNT',

    headerName: 'ORDER AMOUNT',
    type: 'number',
    headerAlign: 'left',
    align: 'left',
    sortable: true,
    width: 180,
    editable: true,
  },
  {
    field: 'ORDER_CURRENCY',

    headerName: 'ORDER CURRENCY',
    headerAlign: 'left',
    align: 'left',
    sortable: true,
    width: 195,
    editable: true,
  },
  {
    field: 'CUSTOMER_NUMBER',

    headerName: 'CUSTOMER NUMBER',
    headerAlign: 'left',
    align: 'left',
    sortable: true,
    width: 210,
    editable: true,
  },
  {
    field: 'AMOUNT_IN_USD',

    headerName: 'AMOUNT IN USD',
    headerAlign: 'left',
    align: 'left',
    sortable: true,
    width: 180,
    editable: true,
  },
];

export default columns;
import React from 'react';
import Table from './Table/Table.jsx';
import PropTypes from 'prop-types';

export const table = <Table className="orderTable"
    tableHeaderColor="primary"
    tableHead={['Quantity','Name','Price']}
    tableData={[]}
/>

// table.props.tableData[0][0] = "my rice";

// console.log("Table: ", table.props.tableData[0][0]);


// [
//     [ "Dakota Rice" , "Niger" , "Oud-Turnhout" , "$36,738" ] ,
//     [ "Minerva Hooper" , "Curaçao" , "Sinaai-Waas" , "$23,789" ] ,
//     [ "Sage Rodriguez" , "Netherlands" , "Baileux" , "$56,142" ] ,
//     [ "Philip Chaney" , "Korea, South" , "Overland Park" , "$38,735" ] ,
//     [ "Doris Greene" , "Malawi" , "Feldkirchen in Kärnten" , "$63,542" ] ,
//     [ "Mason Porter" , "Chile" , "Gloucester" , "$78,615" ]
// ]


// table.defaultProps = {
//     tableHeaderColor: 'gray'
// }

// table.propTypes = {
//     tableHeaderColor: PropTypes.oneOf(['warning','primary','danger','success','info','rose','gray']),
//     tableHead: PropTypes.arrayOf(PropTypes.string),
//     tableData: PropTypes.arrayOf(PropTypes.arrayOf(PropTypes.string))
// }; 
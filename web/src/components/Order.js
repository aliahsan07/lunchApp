import React from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { submitOrder } from './util/APIUtils';

class Order extends React.Component{

    unitId = React.createRef();
    quantity = React.createRef();
    totalPriceRef = React.createRef();


    renderOrder = key => {

        const item = this.props.items[key];
        const count = this.props.order[key];
        const id = this.props.items[key]['itemId'];

        if (count <= 0){
            return;
        }

        // return <li className="list-group-item" key={key} > 
        //     {count}x  {item.title}
        //     ---------------------------------
        //     {count * item.unitPrice} Rs
        // </li>

        return <tr>
            <th scope="row">{count}</th>
            <td>{item.title}</td>
            <td>{count * item.unitPrice}</td>
        </tr>
    }


    handleSubmit = (event) => {
        
        event.preventDefault();
        var orderData = {};
        var orderList = []

        const orderDetails = this.props['order']; 
        for (var i in orderDetails){
            orderList.push({
                itemId: this.props['items'][i]['itemId'],
                quantity: orderDetails[i]
            });
        }
        orderData['orderList'] = orderList;
        orderData['token'] = this.props.token;
        //orderData['totalPrice'] = this.totalPriceRef.current.value;

        //console.log(this.props.token);


        if (window.confirm('Are you sure you want to place your Order?')){  
            submitOrder(orderData).then(response => toast("Your order has been submitted")).then(this.props.clearOrder());
        }

    }

    render(){
        
        const orderIds = Object.keys(this.props.order);

        const isEnabled = orderIds.length > 0 ;

        const total = orderIds.reduce( (prevTotal, key) => {
            const item = this.props.items[key];
            const count = this.props.order[key];

            return prevTotal + (count * item.unitPrice);
        }, 0);

        return(
            <div className="order-wrap">
                <ToastContainer/>
                <form  className="create-order-form" onSubmit={this.handleSubmit}>
                    <h2>Order</h2>
                    <table class="table">
                        <thead class="black white-text">
                            <tr>
                                <th scope="col">Quantity</th>
                                <th scope="col">Name</th>
                                <th scope="col">Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            {orderIds.map(this.renderOrder)}
                        </tbody>
                    </table>
                    <div className="total" ref={this.totalPriceRef} value={total}>
                        Total: <strong>{total} Rs</strong>
                    </div>
                    <button type="primary" className="create-order-form-button" disabled={!isEnabled}>Submit Order</button>
                </form>
            

            </div>
        )
    }
}


export default Order;
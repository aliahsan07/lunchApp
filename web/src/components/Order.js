import React from 'react';
import { submitOrder } from './util/APIUtils';

class Order extends React.Component{

    unitId = React.createRef();
    quantity = React.createRef();
    totalPriceRef = React.createRef();



    // state = {
    //     order: {}
    // }


    // componentDidUpdate(prevProps) {

    //     if (this.props.order !== prevProps.order){
    //         this.setState({order: this.props.order})
    //     }


    // }

    

    // handleClick = key => {
        
    //     const order = {...this.state.order};
    //     order[key] = order[key] -  1;
    //     this.setState({order});
        

    // }


    renderOrder = key => {

        const item = this.props.items[key];
        const count = this.props.order[key];
        const id = this.props.items[key]['itemId'];

        if (count <= 0){
            return;
        }


        return <li key={key} > 
            {count}x  {item.title}
            ---------------------------------
            {count * item.unitPrice} Rs
            {/* <button className="decrease-count" onClick={() => this.handleClick(key)}>-</button> */}
        </li>
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

        //submitOrder(orderData).then(response => console.log(response));


    }

    render(){
        
        const orderIds = Object.keys(this.props.order);

        const total = orderIds.reduce( (prevTotal, key) => {
            const item = this.props.items[key];
            const count = this.props.order[key];

            return prevTotal + (count * item.unitPrice);
        }, 0);

        return(
            <div className="order-wrap">
                <form  className="create-order-form" onSubmit={this.handleSubmit}>
                    <h2>Order</h2>
                    <ul className="order" >
                        {orderIds.map(this.renderOrder)}
                    </ul>
                    <div className="total" ref={this.totalPriceRef} value={total}>
                        Total: <strong>{total} Rs</strong>
                    </div>
                    <button type="primary" className="create-order-form-button">Submit Order</button>
                </form>
            

            </div>
        )
    }
}


export default Order;
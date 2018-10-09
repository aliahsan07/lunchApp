import React from 'react';


class FoodItem extends React.Component{


    state = {
        value: 0
    }


    handleClick = () => {
        this.props.addToOrder(this.props.index);
        let value= this.state.value
        value = value + 1;
        this.setState({value});
    }

    handleRemove = () => {
        this.props.deductFromOrder(this.props.index);
        let value= this.state.value
        value = value - 1;
        this.setState({value});
    }

    render(){
        // const {image, name, price, description, vendor } = this.props.details;
        const name = this.props.details.title;
        const price = this.props.details.unitPrice;
        const isEnabled = this.state.value > 0;
        return ( 
        <li className="single-item"> 
            {/* <img src={image} alt={name}/> */}
            <h3 className="title"> {name}
                <span className="price">{price} Rs</span>
            </h3>
            {/* <p>{description}</p>
            <h3>{vendor}</h3> */}
            <button onClick={this.handleClick} >Add To Cart</button>
            <button disabled={!isEnabled} onClick={this.handleRemove}> Deduct From Cart</button>
        </li>
        );
    }
}

export default FoodItem;
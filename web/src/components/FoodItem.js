import React from 'react';


class FoodItem extends React.Component{

    handleClick = () => {
        this.props.addToOrder(this.props.index);
    }

    render(){
        // const {image, name, price, description, vendor } = this.props.details;
        const name = this.props.details.title;
        const price = this.props.details.unitPrice;
        return ( 
        <li className="single-item"> 
            {/* <img src={image} alt={name}/> */}
            <h3 className="title"> {name}
                <span className="price">{price} Rs</span>
            </h3>
            {/* <p>{description}</p>
            <h3>{vendor}</h3> */}
            <button onClick={this.handleClick} >Add To Cart</button>
        </li>
        );
    }
}

export default FoodItem;
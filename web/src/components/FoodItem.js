import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';


const styles = {
    card: {
      maxWidth: 100,
    },
    media: {
      // ⚠️ object-fit is not supported by IE11.
      objectFit: 'cover',
    },
  };

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
        const image = this.props.details.imageUrl;
        const isEnabled = this.state.value > 0;

        return ( 

            <Card className={name}>
            <CardActionArea>
              <CardMedia
                component="img"
                alt="name"
                className={name}
                height="140"
                image={image}
                title={name}
              />
    
              <CardContent>
              <Typography gutterBottom variant="h5" component="h2">
                {name}
              </Typography>
              <Typography component="h3" align="right">
                {price} Rs
              </Typography>
            </CardContent>
          </CardActionArea>
          <CardActions>
            <Button size="small" color="primary" onClick={this.handleClick}>
              Add To Card 
            </Button>
            <Button size="small" color="primary" disabled={!isEnabled} onClick={this.handleRemove}>
              Remove From Cart
            </Button>
          </CardActions>
        </Card>


        // --------------------------------------------------------------- 


        // <li className="list-group-item"> 
        //     <img src={image} alt={name}/>
        //     <h3 className="title"> {name}
        //         <span className="price">{price} Rs</span>
        //     </h3>
        //     {/* <p>{description}</p>
        //     <h3>{vendor}</h3> */}
        //     <button type="button" className="btn btn-primary" onClick={this.handleClick} >Add To Cart</button>
        //     <button type="button" className="btn btn-danger" disabled={!isEnabled} onClick={this.handleRemove}> Deduct From Cart</button>
        // </li>
        );
    }
}

FoodItem.propTypes = {
    classes: PropTypes.object.isRequired,
};


export default FoodItem;
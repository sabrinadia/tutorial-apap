import React from 'react';
import listItems from "../../items.json";
import List from "../../components/list/index.js";
import "./index.css";
import Badge from "@material-ui/core/Badge";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { Fab } from "@material-ui/core";
import ViewStreamIcon from '@mui/icons-material/ViewStream';


export default class Home extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        shopItems: listItems,
        cartItems: [],
        cartHidden: true,
        balance: 120,
      };
    }
   
    handleToggle = () => {
      const cartHidden = this.state.cartHidden;
      this.setState({ cartHidden: !cartHidden });
    };
   
    handleAddItemToCart = (item) => {
        const finalItems = [...this.state.cartItems];
        const newItem = { ...item };
        const targetInd = finalItems.findIndex((it) => it.id === newItem.id);
        if (targetInd < 0) {
            if(this.state.balance<newItem.price){
                alert('Balance not sufficient!');
            }
            else if(this.state.balance >= newItem.price){
                this.setState({balance: this.state.balance - newItem.price})
                newItem.inCart = true;
                finalItems.push(newItem);
                this.updateShopItem(newItem, true);
            }
        }
        this.setState({ cartItems: finalItems });
      };
   
  
    handleDeleteItemToCart = (item) => {
      const remItems = [...this.state.cartItems]; //remainingitems
      const newItem = { ...item };
      const targetInd = remItems.findIndex((it) => it.id === newItem.id);
      if (targetInd >= 0) {
        this.setState({balance: this.state.balance + newItem.price})
        newItem.inCart = false;
        remItems.splice(targetInd,1);
        this.updateShopItem(newItem, false);
      }
      this.setState({ cartItems: remItems });
    };

    handleDeleteAllItemFromCart = (item) => {
      for(var i =0; i< this.state.cartItems.length; i++){
        const newItem = this.state.cartItems[i];
        // var price = newItem.price;
        this.updateShopItem(newItem, false);
        this.setState({balance: this.state.balance + newItem.price})
      }
      this.setState({ cartItems: []});
    };
  
    updateShopItem = (item, inCart) => {
      const tempShopItems = this.state.shopItems;
      const targetInd = tempShopItems.findIndex((it) => it.id === item.id);
      tempShopItems[targetInd].inCart = inCart;
      this.setState({ shopItems: tempShopItems });
    };
   
    render() {
      return (
        <div className="container-fluid">
          <h1 className="text-center mt-3 mb-0">Mini Commerce</h1>
          <div style={{ position: "fixed", top: 25, right: 25 }}>
            <Fab variant="extended" onClick={this.handleToggle}>
              {this.state.cartHidden ? (
                <Badge
                  color="secondary"
                  badgeContent={this.state.cartItems.length}
                >
                  <ShoppingCartIcon />
                </Badge>
              ) : (
                <ViewStreamIcon />
              )}
            </Fab>
          </div>
          <p className="text-center text-secondary text-sm font-italic">
            (this is a <strong>class-based</strong> application)
          </p>
          <p className="text-center text-primary">
            Your Balance: <b> {this.state.balance}</b>{" "}
          </p>
          <div className="container pt-3">
            <div className="row mt-3">
              {!this.state.cartHidden ? (
                <div className="col-sm">
                  <List
                    title="My Cart"
                    items={this.state.cartItems}
                    onItemClick={this.handleDeleteItemToCart}
                  ></List>
                  <button OnClick={this.handleDeleteAllItemFromCart}> Delete All Item </button>
                </div>
              ) : (
                <div className="col-sm">
                  <List
                    title="List Items"
                    items={this.state.shopItems}
                    onItemClick={this.handleAddItemToCart}
                    isShopList={true}
                  ></List>
                </div>
              )}
            </div>
          </div>
        </div>
      );
    }
  }
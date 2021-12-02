import React, { Component } from "react";
import Item from "../../components/Item";
import Search from "../../components/search";
import classes from "./styles.module.css";
import APIConfig from "../../api/APIConfig";
import Button from "../../components/button";
import Modal from "../../components/modal";
import Badge from "@material-ui/core/Badge";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { Fab } from "@material-ui/core";
import ViewStreamIcon from '@mui/icons-material/ViewStream';
// import List from "../../components/list/index.js";

class ItemList extends Component{
    constructor(props){
        super(props);
        this.state = {
            items:[],
            isLoading: false,
            isCreate: false,
            isEdit: false,
            id: "",
            title: "",
            price: 0,
            description: "",
            category: "",
            quantity: 0,
            cartItems: [],
            cartHidden: true,
        };
        this.handleAddItem = this.handleAddItem.bind(this);
        this.handleCancel = this.handleCancel.bind(this);
        this.handleChangeField = this.handleChangeField.bind(this);
        this.handleSubmitItem = this.handleSubmitItem.bind(this);
        this.loadData = this.loadData.bind(this);
        this.handleSubmitEditItem = this.handleSubmitEditItem.bind(this);
        this.searchItem = this.searchItem.bind(this);
        this.handleToggle = this.handleToggle.bind(this);
    }
    componentDidMount() {
       this.loadData();
    }

    handleToggle = () => {
        const cartHidden = this.state.cartHidden;
        this.setState({ cartHidden: !cartHidden });
    };

    handleAddItem() {
        this.setState({ isCreate:true });
    }
    handleCancel(event) {
        event.preventDefault();
        this.setState({ isCreate:false, isEdit: false });
    }

    handleChangeField(event) {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    handleEditItem(item) {
        this.setState({
            isEdit: true,
            id: item.id,
            title: item.title,
            price: item.price,
            description: item.description,
            category: item.category,
            quantity: item.quantity
        })
    }

    async searchItem(text){
        if(text === "") this.loadData();
        else{
            const results = this.state.items.filter(item => {
                return item.title.toLowerCase().includes(text.toLowerCase())
            })
            this.setState({input : text});
            this.setState({ items : results})
        }

    }

    async loadData(){
        try {
            const { data } = await APIConfig.get("/item");
            this.setState({ items: data.result });
            } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
            }
    }

    async handleSubmitItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            await APIConfig.post("/item", data);
            this.setState({
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0
            })
            this.loadData();
            } catch (error) {
                alert("Oops terjadi masalah pada server");
                console.log(error);
        }
        this.handleCancel(event);
    }

    async handleSubmitEditItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            await APIConfig.put(`/item/${this.state.id}`, data);
            this.setState({
                id: "",
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0
            })
            this.loadData();
            } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.handleCancel(event);
}

render() {

    return(
        <div className={classes.itemList}>
            <h1 className={classes.title}>All Items</h1>
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
            {/* <div className="container pt-3">
            <div className="row mt-3">
              {!this.state.cartHidden ? (
                <div className="col-sm">
                  <List
                    title="My Cart"
                    items={this.state.cartItems}
                    onItemClick={this.handleDeleteItemToCart}
                  ></List>
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
          </div> */}
          </div>
            <Button action={this.handleAddItem}>
                Add Item
            </Button>
            <br>
            </br>
            <Search handleChange={(e) => this.searchItem(e.target.value)}></Search>
            <div>
                {this.state.items.map((item) => (
                    <Item
                    key={item.id}
                    id={item.id}
                    title={item.title}
                    price={item.price}
                    description={item.description}
                    category={item.category}
                    handleEdit={ () => this.handleEditItem(item)}
                    />
                ))}
            </div>
        
            <Modal
                show={this.state.isCreate || this.state.isEdit}
                handleCloseModal={this.handleCancel}
                modalTitle={this.state.isCreate
                    ?"Add Item"
                    : `Edit Item ID ${this.state.id}`}
            >
                <form>
                    <input
                    className={classes.textField}
                    type="text"
                    placehorder="Nama Item"
                    name="title"
                    value={this.state.title}
                    onChange={this.handleChangeField}
                    />
                    <input
                    className={classes.textField}
                    type="number"
                    placeholder="Harga"
                    name="price"
                    value={this.state.price}
                    onChange={this.handleChangeField}
                    />
                    <input
                    className={classes.textField}
                    placeholder="Deskripsi"
                    name="description"
                    rows="4"
                    value={this.state.description}
                    onChange={this.handleChangeField}
                    />
                    <input
                    className={classes.textField}
                    type="text"
                    placeholder="Kategori"
                    name="category"
                    value={this.state.category}
                    onChange={this.handleChangeField}
                    />
                    <input
                    className={classes.textField}
                    type="number"
                    placeholder="qty"
                    name="quantity"
                    value={this.state.quantity}
                    onChange={this.handleChangeField}
                    />
                    <Button action={this.state.isCreate
                        ? this.handleSubmitItem
                        : this.handleSubmitEditItem}
                    >
                        Create
                    </Button>
                    <Button action={this.handleCancel}>
                        Cancel
                    </Button>
                </form>
            </Modal>
        </div>
    );
}

    
}

export default ItemList;

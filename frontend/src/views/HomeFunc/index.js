import listItems from "../../items.json";
import List from "../../components/list/index";
import React, { useState } from "react";
import "./index.css";
import Badge from "@material-ui/core/Badge";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { Fab } from "@material-ui/core";
import ViewStreamIcon from '@mui/icons-material/ViewStream';
 
/**
* Building React component using functional programming paradigm
*/
function App() {
// Btw, this is hooks. useState function returns an array
// contains the state and a function to set the state -> [state, setState].
// What you see below is array destruction.
// Let say you have an array const arr = ["aaa", "bbb"], to access the item
// we can use index arr[0] OR destruct it like below
// const [varName, index1] = arr, variable varName is guaranteed to get
// the value of index 0 OR "aaa"
// here is the illustration for this situation
// below is the return value of useState
 
   const [shopItems, setShopItems] = useState(() => listItems);
   const [cartItems, setCartItems] = useState(() => []);
   const [cartHidden, setCartHidden] = useState(true);
   const [balance, setBalance] = useState(120);
  
   function updateShopItem(item, inCart){
       const tempShopItems = [...shopItems];
       const targetInd = tempShopItems.findIndex((it) => it.id === item.id);
       tempShopItems[targetInd].inCart = inCart;
       setShopItems(tempShopItems);
   }


   function updateBalance(price, operation){
       if(operation == "add"){
            if (balance < price){
                alert('Balance not sufficient!');
                return false;
            }
            const result = balance - price;
            setBalance(result);
            return true;
       }
       else if(operation = "delete"){
            const result = balance + price;
            setBalance(result);
            return 1;
       } 
   }

 
   function handleToggle(){
       setCartHidden(!cartHidden);
   }
 
   function handleAddItemToCart(item){
    if(updateBalance(item.price, "add") == true){
        const newItems = [...cartItems];
        const newItem = { ...item };
        const targetInd = newItems.findIndex((it) => it.id === newItem.id);

        if (targetInd < 0) {
            if (updateBalance(newItem.price, "add") == true){
                newItem.inCart = true;
                newItems.push(newItem);
                updateShopItem(newItem, true)
            }
            
        }
        setCartItems(newItems);
    }
    
}

function deleteItemFromCart(item){
    const remItems = [...cartItems]; //remainingItems
    const newItem = { ...item };
    const targetInd = remItems.findIndex((it) => it.id === newItem.id);
    if (targetInd >= 0) {
      newItem.inCart = false;
      updateBalance(newItem.price, "delete");
      remItems.splice(targetInd,1);
      updateShopItem(newItem, false);
    }
     setCartItems(remItems);
    
  };
   
 
   return (
       <div className="container-fluid">
           <h1 className="text-center mt-3 mb-0">Mini Commerce</h1>
           <div style={{ position: "fixed", top: 25, right: 25 }}>
               <Fab variant="extended" onClick={handleToggle}>
                   {cartHidden ?
                   <Badge color="secondary" badgeContent={cartItems.length}>
                           <ShoppingCartIcon />
                       </Badge>
                       : <ViewStreamIcon/>}
               </Fab>
           </div>
 
           <p className="text-center text-secondary text-sm font-italic">
               (this is a <strong>function-based</strong> application)
           </p>
 
           <p className="text-center text-primary" >Your Balance: <b> {balance}</b> </p>
 
           <div className="container pt-3">
               <div className="row mt-3">
                   {!cartHidden ? (
                       <div className="col-sm">
                           <List
                               title="My Cart"
                               items={cartItems}
                               onItemClick={deleteItemFromCart}
                           ></List>
                       </div>
                   ) : <div className="col-sm">
                       <List
                           title="List Items"
                           items={shopItems}
                           onItemClick={handleAddItemToCart}
                           isShopList={true}
                       ></List>
                   </div>}
               </div>
           </div>
       </div>
   );
}
 
export default App;
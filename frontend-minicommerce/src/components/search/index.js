import React from "react";

const Search= (props) => {
    return (
        <input
            type="search"
            placeholder={"Find Item"}
            onChange={props.handleChange}
        />
    );
}

export default Search;
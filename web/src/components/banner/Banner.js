import React from "react";

export default function Banner(props) {

    return(
        <div className="banner">
            <h3>{props.text}</h3>
        </div>
    );
}
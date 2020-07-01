import React from 'react'


class SerieDisplay extends React.Component{

    render(){
        
        return(
            <div>
                <h1>{this.props.serie.title}</h1>
                <img src = {this.props.serie.poster} alt={this.props.serie.title}/>
                <p>{this.props.serie.description}</p>
                <h5>Seasons</h5>

                
            </div>
        )
    }
    
}

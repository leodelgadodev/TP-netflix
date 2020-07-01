import React from 'react'
import Video from './Video'

class MovieDisplay extends React.Component{
    constructor(props){
        super(props)
    }
    render(){
        console.log(this.props);
        return(
            <div class="media">
                
                <div class="media-body">
                <h5 class="mt-0">{this.props.movie.title}</h5>
                    <p>{this.props.movie.description}</p>
                    <button onClick = {Video(this.props)}>Play</button>
                </div>
            </div>
        )
    }
    
}// <img src = {this.props.movie.poster} class="mr-3" alt={this.props.movie.title}/>
export default MovieDisplay;
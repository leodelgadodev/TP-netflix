import React from 'react';
import {useLocation} from 'react-router-dom'

function useQuery() {
    return new URLSearchParams(useLocation().search);
}

export default function SearchPage() {

    //estaba probando si funcaba att: Nacho :D
    const query = useQuery();
    return(<div>{query.get("content")}</div>);
    
}


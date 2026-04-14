import { decode, encode } from "../Base64/Base64";

export const apiCall = async (url, method, data) => {
    let encodedData = encode(JSON.stringify(data));
    let requestResponse ={
        method: method,
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "data": encodedData
        })   
    }
    let enc = encode(JSON.stringify(data))
    console.log("enc dec", enc, " +++++++ ",decode(enc))
    const response = await fetch(url, requestResponse);
    return response;
}
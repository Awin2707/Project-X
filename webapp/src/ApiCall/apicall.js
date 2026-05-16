export const API_CALL = async (data, method, url) => {
    let response = await fetch(url, {
        method: method,
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        body: data ? JSON.stringify(data) : null
    });
    console.log(data);
    try {
        const result = await response.json();
        return {response, result};
    } catch (error) {
        console.log(error)
    }
    try {
        const result = await response.text();
        return {response, result};
    } catch (error) {
        console.log(error)
    }
}
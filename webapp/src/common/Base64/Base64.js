const DEFAULT_VALUE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
const key = "myKeySecret";

const createTable = () => {
    const set = new Set();

    for (const element of key) {
        set.add(element);
    }

    for (const element of DEFAULT_VALUE) {
        set.add(element);
    }

    return [...set].join('');
};

export const encode = (data) => {
    let ans = "";
    const encoded = btoa(data);
    const table = createTable();

    for (const ele of encoded) {
        const i = DEFAULT_VALUE.indexOf(ele);
        ans += (i !== -1) ? table.charAt(i) : ele;
    }

    return ans;
};

export const decode = (data) => {
    let ans = "";
    const table = createTable();

    const map = new Map();
    for (let i = 0; i < table.length; i++) {
        map.set(table.charAt(i), DEFAULT_VALUE.charAt(i));
    }

    for (const element of data) {
        ans += map.get(element) || element;
    }

    let decodedString;
    try {
        decodedString = atob(ans);
    } catch {
        return ans;
    }

    try {
        return JSON.parse(decodedString);
    } catch {
        return decodedString;
    }
};
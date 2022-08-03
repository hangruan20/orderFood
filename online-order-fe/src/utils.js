export const login = (info) => {
    // fetch api
    const loginUrl = `/login?username=${info.username}&password=${info.password}`;

    // fetch的返回值是个promise
    return fetch(loginUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        credentials: "include",
    }).then((response) => {
        if(response.status < 200 || response.status >= 300) {
            throw Error("Fail to log in");
        }
    });
};

export const signup = (info) => {
    const signupUrl = "/signup";

    return fetch(signupUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(info),
    }).then((response) => {
        if(response.status < 200 || response.status >= 300) {
            throw Error("Fail to sign up");
        }
    });
};

export const getMenus = (restId) => {
    const getMenusUrl = `/restaurant/${restId}/menu`;
    return fetch(getMenusUrl).then((response) => {
        if(response.status < 200 || response.status >= 300) {
            throw Error("Fail to get menus");
        }

        return response.json();
    });
};

export const getRestaurants = () => {
    return fetch("/restaurants").then((response) => {
      if (response.status < 200 || response.status >= 300) {
        throw Error("Fail to get restaurants");
      }
  
      return response.json();
    });
};
  
export const getCart = () => {
    return fetch("/cart").then((response) => {
      if (response.status < 200 || response.status >= 300) {
        throw Error("Fail to get shopping cart data");
      }
  
      return response.json();
    });
};
  
export const checkout = () => {
    return fetch("/checkout").then((response) => {
      if (response.status < 200 || response.status >= 300) {
        throw Error("Fail to checkout");
      }
    });
};

export const addItemToCart = (itemId) => {
    return fetch(`/order/${itemId}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      credentials: "include",
    }).then((response) => {
      if (response.status < 200 || response.status >= 300) {
        throw Error("Fail to add menu item to shopping cart");
      }
    });
};
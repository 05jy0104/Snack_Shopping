const API_BASE_URL = 'http://localhost:8080/api';

async function request(url, options = {}) {
    const token = localStorage.getItem('token');
    const headers = {
        'Content-Type': 'application/json',
        ...options.headers
    };
    
    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }

    const response = await fetch(`${API_BASE_URL}${url}`, {
        ...options,
        headers
    });

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    return response.json();
}

const api = {
    user: {
        login: (data) => request('/user/login', {
            method: 'POST',
            body: JSON.stringify(data)
        }),
        register: (data) => request('/user/register', {
            method: 'POST',
            body: JSON.stringify(data)
        }),
        logout: () => request('/user/logout', {
            method: 'GET'
        }),
        getInfo: () => request('/user/info', {
            method: 'GET'
        }),
        updateProfile: (data) => request('/user/profile', {
            method: 'POST',
            body: JSON.stringify(data)
        }),
        snack: {
            list: (params = {}) => {
                const queryString = new URLSearchParams(params).toString();
                return request(`/user/snack/list${queryString ? '?' + queryString : ''}`, {
                    method: 'GET'
                });
            },
            detail: (id) => request(`/user/snack/detail/${id}`, {
                method: 'GET'
            })
        },
        cart: {
            add: (data) => request('/user/cart/add', {
                method: 'POST',
                body: JSON.stringify(data)
            }),
            list: () => request('/user/cart/list', {
                method: 'GET'
            }),
            delete: (id) => request(`/user/cart/delete/${id}`, {
                method: 'GET'
            }),
            clear: () => request('/user/cart/clear', {
                method: 'GET'
            })
        },
        order: {
            list: () => request('/user/order/list', {
                method: 'GET'
            }),
            detail: (id) => request(`/user/order/detail/${id}`, {
                method: 'GET'
            }),
            create: (data) => request('/user/order/create', {
                method: 'POST',
                body: JSON.stringify(data)
            })
        }
    },
    admin: {
        login: (data) => request('/admin/login', {
            method: 'POST',
            body: JSON.stringify(data)
        }),
        logout: () => request('/admin/logout', {
            method: 'GET'
        }),
        getInfo: () => request('/admin/info', {
            method: 'GET'
        }),
        category: {
            list: () => request('/admin/category/list', {
                method: 'GET'
            }),
            add: (data) => request('/admin/category/add', {
                method: 'POST',
                body: JSON.stringify(data)
            }),
            edit: (id) => request(`/admin/category/edit/${id}`, {
                method: 'GET'
            }),
            update: (data) => request('/admin/category/edit', {
                method: 'POST',
                body: JSON.stringify(data)
            }),
            delete: (id) => request(`/admin/category/delete/${id}`, {
                method: 'GET'
            })
        },
        snack: {
            list: () => request('/admin/snack/list', {
                method: 'GET'
            }),
            add: (data) => request('/admin/snack/add', {
                method: 'POST',
                body: JSON.stringify(data)
            }),
            edit: (id) => request(`/admin/snack/edit/${id}`, {
                method: 'GET'
            }),
            update: (data) => request('/admin/snack/edit', {
                method: 'POST',
                body: JSON.stringify(data)
            }),
            delete: (id) => request(`/admin/snack/delete/${id}`, {
                method: 'GET'
            })
        },
        user: {
            list: () => request('/admin/user/list', {
                method: 'GET'
            }),
            delete: (id) => request(`/admin/user/delete/${id}`, {
                method: 'GET'
            })
        },
        order: {
            list: () => request('/admin/order/list', {
                method: 'GET'
            }),
            updateStatus: (data) => request('/admin/order/updateStatus', {
                method: 'POST',
                body: JSON.stringify(data)
            })
        }
    }
};

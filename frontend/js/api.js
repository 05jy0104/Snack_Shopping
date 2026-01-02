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
            add: (data) => {
                const user = JSON.parse(localStorage.getItem('user'));
                console.log('=== API Cart Add Debug ===');
                console.log('Original data:', data);
                console.log('User:', user);
                if (user && user.id) {
                    data.userId = user.id;
                }
                console.log('Modified data:', data);
                return request('/user/cart/add', {
                    method: 'POST',
                    body: JSON.stringify(data)
                });
            },
            list: () => {
                const user = JSON.parse(localStorage.getItem('user'));
                const params = user && user.id ? { userId: user.id } : {};
                const queryString = new URLSearchParams(params).toString();
                return request(`/user/cart/list${queryString ? '?' + queryString : ''}`, {
                    method: 'GET'
                });
            },
            delete: (id) => request(`/user/cart/delete/${id}`, {
                method: 'GET'
            }),
            clear: () => {
                const user = JSON.parse(localStorage.getItem('user'));
                const params = user && user.id ? { userId: user.id } : {};
                const queryString = new URLSearchParams(params).toString();
                return request(`/user/cart/clear${queryString ? '?' + queryString : ''}`, {
                    method: 'GET'
                });
            }
        },
        order: {
            list: () => {
                const user = JSON.parse(localStorage.getItem('user'));
                const params = user && user.id ? { userId: user.id } : {};
                const queryString = new URLSearchParams(params).toString();
                return request(`/user/order/list${queryString ? '?' + queryString : ''}`, {
                    method: 'GET'
                });
            },
            detail: (id) => request(`/user/order/detail/${id}`, {
                method: 'GET'
            }),
            create: (data) => {
                const user = JSON.parse(localStorage.getItem('user'));
                if (user && user.id) {
                    data.userId = user.id;
                }
                return request('/user/order/create', {
                    method: 'POST',
                    body: JSON.stringify(data)
                });
            },
            return: (data) => request('/user/order/return', {
                method: 'POST',
                body: JSON.stringify(data)
            }),
            exchange: (data) => request('/user/order/exchange', {
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

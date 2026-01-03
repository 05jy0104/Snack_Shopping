class Notification {
    constructor() {
        this.container = null;
        this.init();
    }

    init() {
        this.container = document.createElement('div');
        this.container.id = 'notification-container';
        document.body.appendChild(this.container);
    }

    show(message, type = 'info', duration = 3000) {
        const notification = document.createElement('div');
        notification.className = `notification notification-${type}`;
        
        const icon = this.getIcon(type);
        
        notification.innerHTML = `
            <div class="notification-content">
                <span class="notification-icon">${icon}</span>
                <span class="notification-message">${message}</span>
            </div>
        `;

        this.container.appendChild(notification);

        setTimeout(() => {
            notification.classList.add('show');
        }, 10);

        setTimeout(() => {
            notification.classList.remove('show');
            setTimeout(() => {
                notification.remove();
            }, 300);
        }, duration);
    }

    getIcon(type) {
        const icons = {
            success: '✓',
            error: '✕',
            warning: '⚠',
            info: 'ℹ'
        };
        return icons[type] || icons.info;
    }

    success(message, duration) {
        this.show(message, 'success', duration);
    }

    error(message, duration) {
        this.show(message, 'error', duration);
    }

    warning(message, duration) {
        this.show(message, 'warning', duration);
    }

    info(message, duration) {
        this.show(message, 'info', duration);
    }

    confirm(message, onConfirm, onCancel) {
        const confirmModal = document.createElement('div');
        confirmModal.className = 'confirm-modal';
        confirmModal.innerHTML = `
            <div class="confirm-content">
                <div class="confirm-icon">?</div>
                <div class="confirm-message">${message}</div>
                <div class="confirm-buttons">
                    <button class="confirm-btn confirm-btn-cancel">取消</button>
                    <button class="confirm-btn confirm-btn-confirm">确定</button>
                </div>
            </div>
        `;

        document.body.appendChild(confirmModal);

        setTimeout(() => {
            confirmModal.classList.add('show');
        }, 10);

        const confirmBtn = confirmModal.querySelector('.confirm-btn-confirm');
        const cancelBtn = confirmModal.querySelector('.confirm-btn-cancel');

        confirmBtn.onclick = () => {
            confirmModal.classList.remove('show');
            setTimeout(() => {
                confirmModal.remove();
            }, 300);
            if (onConfirm) onConfirm();
        };

        cancelBtn.onclick = () => {
            confirmModal.classList.remove('show');
            setTimeout(() => {
                confirmModal.remove();
            }, 300);
            if (onCancel) onCancel();
        };
    }

    modal(title, content, buttons) {
        const modal = document.createElement('div');
        modal.className = 'custom-modal';
        modal.innerHTML = `
            <div class="custom-modal-content">
                <div class="custom-modal-header">
                    <h3 class="custom-modal-title">${title}</h3>
                    <button class="custom-modal-close">&times;</button>
                </div>
                <div class="custom-modal-body">
                    ${content}
                </div>
                <div class="custom-modal-footer">
                    ${buttons.map(btn => `<button class="custom-modal-btn ${btn.className || ''}">${btn.text}</button>`).join('')}
                </div>
            </div>
        `;

        document.body.appendChild(modal);

        setTimeout(() => {
            modal.classList.add('show');
        }, 10);

        const closeBtn = modal.querySelector('.custom-modal-close');
        closeBtn.onclick = () => {
            modal.classList.remove('show');
            setTimeout(() => {
                modal.remove();
            }, 300);
        };

        const modalButtons = modal.querySelectorAll('.custom-modal-btn');
        modalButtons.forEach((btn, index) => {
            btn.onclick = () => {
                if (buttons[index].onClick) {
                    buttons[index].onClick();
                } else {
                    modal.classList.remove('show');
                    setTimeout(() => {
                        modal.remove();
                    }, 300);
                }
            };
        });

        return modal;
    }
}

const notification = new Notification();

export class LoggedUtils {

    static getToken() {
        if (this.isEmpty()) {
            return '';
        }
        return localStorage.getItem('loggedUser');
    }

    static setToken(token: string) {
        localStorage.setItem("loggedUser", token);
    }


    static clearLocalStorage() {
        localStorage.clear();
    }

    static isEmpty() {
        return localStorage.getItem('loggedUser') === null;
    }

    static getRole() {
        if (this.isEmpty()) {
            return null;
        }
        return localStorage.getItem('loggedUser');
    }
}
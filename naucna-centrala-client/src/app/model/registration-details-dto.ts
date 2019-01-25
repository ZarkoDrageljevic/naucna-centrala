export class RegistrationDetailsDto {
    firstname: string;
    lastname: string;
    username: string;
    password: string;
    email: string;
    address: string;
    processInstanceId: string;

    constructor(registration: RegistrationDetailsInterface = {}) {
        this.firstname = registration.firstname;
        this.lastname = registration.lastname;
        this.username = registration.username;
        this.password = registration.password;
        this.email = registration.email;
        this.address = registration.address;
    }
    set setprocessInstanceId(newprocessInstanceId: string){
        this.processInstanceId = newprocessInstanceId;
    }
}

interface RegistrationDetailsInterface {
    firstname?: string;
    lastname?: string;
    username?: string;
    password?: string;
    email?: string;
    address?: string;
}
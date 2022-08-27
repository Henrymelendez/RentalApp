export class User {
    id: number;
    userName: string;
    password: string;
    enabled: boolean;
    role: string;

    constructor(id: number, userName: string, password: string, enabled: boolean, role: string){
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }


}

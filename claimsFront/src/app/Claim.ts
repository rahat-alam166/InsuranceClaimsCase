export interface Claim {
    id: number;
    description: string;
    status: string;
    date: Date;
    open: boolean;
    support: string;
}

export interface AddClaim {
    description: string;
    status: string;
    date: Date;
    open: boolean;
    support: string;
}

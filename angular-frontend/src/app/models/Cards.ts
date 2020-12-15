export class Cards {
    card_id: number;
    card_no: string;
    card_type: string;
    expiry_date: string;
    limit: number;
    status: string;
    issue_date: string;
    joining_fee: number;
    user_id: number;

    // constructor() {

    // }
    constructor(card_id: number, card_no: string, card_type: string, expiry_date: string, limit: number, status: string, issue_date: string, joining_fee: number, user_id: number) {
        this.card_id = card_id;
        this.card_no = card_no;
        this.card_type = card_type;
        this.expiry_date = expiry_date;
        this.limit = limit;
        this.status = status;
        this.issue_date = issue_date;
        this.joining_fee = joining_fee;
        this.user_id = user_id;
    }
}
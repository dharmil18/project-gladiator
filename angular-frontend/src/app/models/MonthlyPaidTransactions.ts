export class MonthlyPaidTransactions {
    mtxn_id: number;
    transaction_id: number;
    product_name: string;
    amount: number;
    mtxn_date: string;
    m_status: string;

    constructor(mtxn_id: number, transaction_id: number, product_name: string, amount: number, mtxn_date: string, m_status: string) {
        this.mtxn_id = mtxn_id;
        this.transaction_id = transaction_id;
        this.product_name = product_name;
        this.amount = amount;
        this.mtxn_date = mtxn_date;
        this.m_status = m_status;
    }
}
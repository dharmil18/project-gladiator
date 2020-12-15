export class Transactions {
    transaction_id: number;
	product_id: number;
	product_name: string;
    product_cost: number;
	monthly_installment: number;
	tenure: number;
	transaction_date: string;
	status: string;
    processing_fee: number;

    constructor(
        transaction_id: number,
        product_id: number,
        product_name: string,
        product_cost: number,
        monthly_installment: number,
        tenure: number,
        transaction_date: string,
        status: string,
        processing_fee: number) 
    {
        this.transaction_id = transaction_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_cost = product_cost;
        this.monthly_installment = monthly_installment;
        this.tenure = tenure;
        this.transaction_date = transaction_date;
        this.status = status;
        this.processing_fee = processing_fee;
    }
}
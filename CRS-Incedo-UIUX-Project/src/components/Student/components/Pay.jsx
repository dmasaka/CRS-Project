import React, { useEffect, useState } from 'react'
import styles from '../Student.module.css'
import { BasNav } from './BasNav'
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { userstore } from '../../../redux/store';


export const Pay = () => {
    const [bank, setBank] = useState('')
    const [check, setCheck] = useState(0)
    const [ctype, setCtype] = useState('')
    const [card, setCard] = useState(0)
    const [amount, setAmount] = useState(0)
    const [refresh, setRefresh] = useState(false);
    const nstore = userstore.getState()

    useEffect(() => {
        fetch( import.meta.env.VITE_BACK + 'pay/student/' + nstore.userid)
            .then(resp => resp.json())
            .then(data => setAmount(data.amount))
    }, [refresh])

    const offpay = e => {
        e.preventDefault()
        fetch( import.meta.env.VITE_BACK + 'pay/offline', {
            method: "POST",
            headers: new Headers({
                "Content-Type":"application/json"
            }),
            body: JSON.stringify({
                "checkNumber":check,
                "bankName":bank,
                "studentId":nstore.userid,
                "amount":200
            })
        })
        .then(() => console.log("offline payment"))
        .then(() => setTimeout(() => {
            setRefresh(!refresh)
        }, 2000))
    }

    const onpay = e => {
        e.preventDefault()
        fetch( import.meta.env.VITE_BACK + 'pay/online', {
            method: "POST",
            headers: new Headers({
                "Content-Type":"application/json"
            }),
            body: JSON.stringify({
                "cardNumber":card,
                "cardType":ctype,
                "studentId":nstore.userid,
                "amount":200
            })
        })
        .then(() => console.log("online payment"))
        .then(() => setTimeout(() => {
            setRefresh(!refresh)
        }, 2000))
    }

    return (
        <>
            <BasNav />
            <div className={styles.Container}>
                <div className={styles.SerCont}>
                    <div className={styles.PayCont}>
                        <p>Amount Due: {amount}</p>
                        <div className={styles.PayInner}>
                            <div className={styles.PayType}>
                                <p>Offline</p>
                                <Form className={styles.FormSin}>
                                    <Form.Group className="mb-3" controlId="formBasicText">
                                        <Form.Label>Bank</Form.Label>
                                        <Form.Control type="text" placeholder="Enter bank" onChange={e => setBank(e.target.value)} value={bank} />
                                    </Form.Group>

                                    <Form.Group className="mb-3" controlId="formBasicNumber">
                                        <Form.Label>Check Number</Form.Label>
                                        <Form.Control type="number" placeholder="Enter check number" onChange={e => setCheck(e.target.value)} value={check} />
                                        <Form.Text className="text-muted">
                                            Enter number written on the back of the check
                                        </Form.Text>
                                    </Form.Group>
                                    <Button variant="primary" type="submit" onClick={offpay}>
                                        Make Payment
                                    </Button>
                                </Form>
                            </div>
                            <div className={styles.PayType}>
                                <p>Online</p>
                                <Form className={styles.FormSin}>
                                    <Form.Group className="mb-3" controlId="formBasicNumber">
                                        <Form.Label>Card Number</Form.Label>
                                        <Form.Control type="number" placeholder="Enter card number" onChange={e => setCard(e.target.value)} value={card} />
                                    </Form.Group>
                                    <Form.Group className="mb-3" controlId="formBasicText">
                                        <Form.Label>Card Type</Form.Label>
                                        <Form.Control type="text" placeholder="Enter card type" onChange={e => setCtype(e.target.value)} value={ctype} />
                                        <Form.Text className="text-muted">
                                            Enter card type e.g Bisa, ServantCard
                                        </Form.Text>
                                    </Form.Group>
                                    <Button variant="primary" type="submit" onClick={onpay}>
                                        Make Payment
                                    </Button>
                                </Form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

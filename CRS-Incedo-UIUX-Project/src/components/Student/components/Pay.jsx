import React, { useState } from 'react'
import styles from '../Student.module.css'
import { BasNav } from './BasNav'
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';


export const Pay = () => {
    const [bank, setBank] = useState('')
    const [check, setCheck] = useState(0)
    const [ctype, setCtype] = useState('')
    const [card, setCard] = useState(0)
    return (
        <>
            <BasNav />
            <div className={styles.Container}>
                <div className={styles.SerCont}>
                    <div className={styles.PayCont}>
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
                                        <Form.Control type="number" placeholder="Enter check number" onChange={e => setCheck(e.target.value)} value={check}/>
                                        <Form.Text className="text-muted">
                                            Enter number written on the back of the check
                                        </Form.Text>
                                    </Form.Group>
                                    <Button variant="primary" type="submit">
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
                                    <Button variant="primary" type="submit">
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

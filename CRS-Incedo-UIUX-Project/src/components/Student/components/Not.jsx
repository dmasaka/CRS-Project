import React, { useEffect, useState } from 'react'
import styles from '../Student.module.css'
import { BasNav } from './BasNav'
import { userstore } from '../../../redux/store'
import { Button } from 'react-bootstrap'

export const Not = () => {
    const codes = {26:"Too Many Classes", 32:"World War 3", 43:"Pay Up"}
    const nstore = userstore.getState()
    const [alerts, setAlerts] = useState([])
    const [refresh, setRefresh] = useState(false)

    useEffect(() => {
      fetch(import.meta.env.VITE_BACK + 'not/' + nstore.userid)
      .then(resp => resp.json())
      .then(data => setAlerts(data))
    }, [refresh])

    const dele = e => {
        fetch(import.meta.env.VITE_BACK + 'not/' + e.target.value, {method:"DELETE"})
        .then(resp => setTimeout(() => {
            setRefresh(!refresh)
        }, 2000))
    }

    return (
        <>
            <BasNav />
            <div className={styles.Container}>
                <div className={styles.SerCont}>
                    <div className={styles.GradeCourse}>
                        <div>
                            <p>Notifications</p>
                        </div>
                        <table>
                            <tbody>
                                <tr>
                                    <th>Notification</th>
                                    <th></th>
                                </tr>
                                {alerts.map(item => (
                                    <tr key={item.notificationId}>
                                        <td>{codes[item.notificationType]}</td>
                                        <td>
                                            <Button variant='danger' type='submit' value={item.notificationId} onClick={dele}>
                                                X
                                            </Button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </>
    )
}

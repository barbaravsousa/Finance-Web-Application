import React from 'react';
import "../../css/AddFamilyModal.css";

export default function PhoneNumbersTable(props) {

    if (props.data.length > 0) {
        const rows = props.data.map(
            (row, index) =>
                <table className="phone-number-table__division">
                    <tr key={index}>
                        <td>{row}</td>
                    </tr>
                </table>

        )
        return (
            <table className="phone-number-table">
                <tbody>{rows}</tbody>
            </table>
        );
    }

    return (
        <div></div>
    );
}

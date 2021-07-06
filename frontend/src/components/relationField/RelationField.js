import React from 'react';

export default function DateField(props) {

    let response = "";

    const validateDate = (date) => {
        if (date !== undefined) {
            if (date.length === 0) response = ""
            else if (new Date(date).valueOf() > new Date().valueOf()) return "Date after today"
            return response;
        }
    }

    return (
        <div>
            <input type="date" id={props.id}
                   required={props.required} onChange={(e) => props.changeHandler(e)}/>
            {validateDate(props.data)}
        </div>
    );
}
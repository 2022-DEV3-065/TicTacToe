import React from "react";
import './TicTacToe.css';

const TicTacToe = () => {

    const [squares, setSquares] = React.useState(Array(9).fill('-'));

    const handleClick = async (num) => {

        console.log(squares);

        const state = squares.slice();
        const json = {state};

        await fetch('/logic', {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({state})
        })
            .then((response) => {
                if (response.status === 200) {
                    return response.json()
                }
            })
            .then((data) => {
                console.log(data);
                setSquares(data.state);
            });

        console.log(squares);

    }

    const Square = ({num}) => {
        return <td
            onClick={() => handleClick(num)} className="square">{squares[num]}
        </td>;
    }

    return (
        <div>
            <table className="board">
                <tbody>
                <tr>
                    <Square num={0}/>
                    <Square num={1}/>
                    <Square num={2}/>
                </tr>
                <tr>
                    <Square num={3}/>
                    <Square num={4}/>
                    <Square num={5}/>
                </tr>

                <tr>
                    <Square num={6}/>
                    <Square num={7}/>
                    <Square num={8}/>
                </tr>

                </tbody>
            </table>
        </div>
    );

}

export default TicTacToe;
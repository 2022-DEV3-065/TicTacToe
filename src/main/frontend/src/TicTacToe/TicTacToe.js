import React from "react";
import './TicTacToe.css';

const TicTacToe = () => {

    const [squares, setSquares] = React.useState(Array(9).fill('-'));
    const [turn, setTurn] = React.useState('X');
    const [winner, setWinner] = React.useState();

    const handleClick = async (squareClicked) => {

        const state = squares.slice();

        await fetch('/logic', {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({state, squareClicked, turn})
        })
            .then((response) => {
                if (response.status === 200) {
                    return response.json()
                }
                if (response.status === 400) {
                    return Promise.reject("Square already played");
                }
            })
            .then((data) => {
                setSquares(data.state);
                setTurn(turn === 'X' ? 'O' : 'X');

                if (data.winner) {
                    setWinner(data.winner);
                }

            }).catch((error) => {
                console.log(error)
            });

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

            <div className="to-play">To play: {turn}</div>
            <div className="winner">Winner: {winner}</div>

        </div>
    );

}

export default TicTacToe;
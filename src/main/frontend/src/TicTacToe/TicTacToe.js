import React from "react";
import './TicTacToe.css';

const TicTacToe = () => {

    const [squares, setSquares] = React.useState(Array(9).fill('-'));
    const [turn, setTurn] = React.useState('X');
    const [winner, setWinner] = React.useState("NONE");

    function resetBoard() {
        setSquares(Array(9).fill('-'));
        setTurn('X');
        setWinner("NONE");
    }
    const handleClick = async (squareClicked) => {

        if (winner === "NONE") {
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
                        return Promise.reject("Bad request");
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
        } else {
            console.log("Game over");
        }
    }

    const Square = ({num}) => {
        return <td
            onClick={() => handleClick(num)} className="square">{squares[num]}
        </td>;
    }

    return (
        <div className="game">
            <div>   <header>
                <h1> Tic-Tac-Toe </h1>
                </header>

            </div>
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

                {/*No winners or draw yet*/}
                {winner !== "X" && winner !== "O" && winner !== "DRAW" &&
                <div className="to-play">
                    <h2>To play: {turn}</h2>
                </div>}

                {/*Winner or draw*/}
                {winner !== "NONE" && winner !== "DRAW" && <div className="winner"><h2>Winner: {winner}</h2></div>}
                {winner === "DRAW" && <div className="winner"><h2>Draw</h2></div>}
                {winner !== "NONE" && <div className="reset">
                    <button onClick={resetBoard}>Reset</button>
                </div>}


            </div>
        </div>

    );

}

export default TicTacToe;
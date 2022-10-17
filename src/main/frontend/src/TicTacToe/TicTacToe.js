import React from "react";
import './TicTacToe.css';

const TicTacToe = () => {

    const Square = () => {
        return <td className="square">-</td>;
    }

    const Row = () => {
        return <tr className="row">
            <Square/>
            <Square/>
            <Square/>
        </tr>
    }

    return (
        <div>
            <table className="board">
                <tbody>
                <Row/>
                <Row/>
                <Row/>
                </tbody>
            </table>
        </div>
    );

}

export default TicTacToe;
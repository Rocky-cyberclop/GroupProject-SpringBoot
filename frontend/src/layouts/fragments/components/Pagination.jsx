import React from 'react';

const Pagination = ({ currentPage, totalPage, onPageChange }) => {

    return (
        <div className="btn-group mt-5" role="group" aria-label="Basic example">
            <button type="button"
                className="btn btn-outline-secondary" onClick={() => onPageChange(1)}>&#8810;</button>
            <button type="button"
                className="btn btn-outline-secondary" onClick={() => onPageChange(currentPage-1)}>&lt;</button>
            <button type="button"
                className="btn btn-outline-secondary">{currentPage}</button>
            <button type="button"
                className="btn btn-outline-secondary" onClick={() => onPageChange(currentPage+1)}>&gt;</button>
            <button type="button"
                className="btn btn-outline-secondary" onClick={() => onPageChange(totalPage)}>&#8811;</button>

        </div>
    );
};

export default Pagination;
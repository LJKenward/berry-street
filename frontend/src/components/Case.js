import React from 'react';
import API from '../api.js';

export default React.createClass({
  getInitialState() {
    return {
      ffCase: {}
    };
  },

  componentDidMount() {
    API.getCases(newCases => {
      this.setState({ffCase: newCases[0]});
    });
  },

  render() {
    return (
      <div>
        <ul>{this.state.ffCase.caseId}</ul>
        <ul>{this.state.ffCase.staffName}</ul>
        <ul>{this.state.ffCase.childName}</ul>
     </div>
    );
  }
});
